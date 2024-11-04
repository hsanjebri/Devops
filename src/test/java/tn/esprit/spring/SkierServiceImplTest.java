package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.*;
import tn.esprit.spring.services.SkierServicesImpl;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkierServicesImplTest {

    @Mock
    private ISkierRepository skierRepository;

    @Mock
    private IPisteRepository pisteRepository;

    @Mock
    private ICourseRepository courseRepository;

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SkierServicesImpl skierServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllSkiers() {
        List<Skier> skiers = List.of(new Skier(), new Skier());
        when(skierRepository.findAll()).thenReturn(skiers);

        List<Skier> result = skierServices.retrieveAllSkiers();

        assertEquals(2, result.size());
        verify(skierRepository, times(1)).findAll();
    }

    @Test
    void addSkier() {
        Subscription subscription = new Subscription();
        subscription.setStartDate(LocalDate.now());
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setSubscription(subscription);

        when(skierRepository.save(any(Skier.class))).thenReturn(skier);

        Skier result = skierServices.addSkier(skier);

        assertNotNull(result);
        assertEquals(LocalDate.now().plusYears(1), result.getSubscription().getEndDate());
        verify(skierRepository, times(1)).save(skier);
    }

    @Test
    void assignSkierToSubscription() {
        Skier skier = new Skier();
        Subscription subscription = new Subscription();

        when(skierRepository.findById(1L)).thenReturn(Optional.of(skier));
        when(subscriptionRepository.findById(1L)).thenReturn(Optional.of(subscription));
        when(skierRepository.save(skier)).thenReturn(skier);

        Skier result = skierServices.assignSkierToSubscription(1L, 1L);

        assertNotNull(result);
        assertEquals(subscription, result.getSubscription());
        verify(skierRepository, times(1)).findById(1L);
        verify(subscriptionRepository, times(1)).findById(1L);
        verify(skierRepository, times(1)).save(skier);
    }

    @Test
    void addSkierAndAssignToCourse() {
        Skier skier = new Skier();
        Course course = new Course();
        Registration registration = new Registration();
        skier.setRegistrations(Set.of(registration));

        when(skierRepository.save(skier)).thenReturn(skier);
        when(courseRepository.getById(1L)).thenReturn(course);
        when(registrationRepository.save(registration)).thenReturn(registration);

        Skier result = skierServices.addSkierAndAssignToCourse(skier, 1L);

        assertNotNull(result);
        assertEquals(course, registration.getCourse());
        verify(skierRepository, times(1)).save(skier);
        verify(courseRepository, times(1)).getById(1L);
        verify(registrationRepository, times(1)).save(registration);
    }

    @Test
    void removeSkier() {
        Long skierId = 1L;
        doNothing().when(skierRepository).deleteById(skierId);

        skierServices.removeSkier(skierId);

        verify(skierRepository, times(1)).deleteById(skierId);
    }

    @Test
    void retrieveSkier() {
        Skier skier = new Skier();
        when(skierRepository.findById(1L)).thenReturn(Optional.of(skier));

        Skier result = skierServices.retrieveSkier(1L);

        assertNotNull(result);
        verify(skierRepository, times(1)).findById(1L);
    }

    @Test
    void assignSkierToPiste() {
        Skier skier = new Skier();
        Piste piste = new Piste();
        Set<Piste> pistes = new HashSet<>();
        skier.setPistes(pistes);

        when(skierRepository.findById(1L)).thenReturn(Optional.of(skier));
        when(pisteRepository.findById(1L)).thenReturn(Optional.of(piste));
        when(skierRepository.save(skier)).thenReturn(skier);

        Skier result = skierServices.assignSkierToPiste(1L, 1L);

        assertNotNull(result);
        assertTrue(result.getPistes().contains(piste));
        verify(skierRepository, times(1)).findById(1L);
        verify(pisteRepository, times(1)).findById(1L);
        verify(skierRepository, times(1)).save(skier);
    }

    @Test
    void retrieveSkiersBySubscriptionType() {
        List<Skier> skiers = List.of(new Skier(), new Skier());
        when(skierRepository.findBySubscription_TypeSub(TypeSubscription.ANNUAL)).thenReturn(skiers);

        List<Skier> result = skierServices.retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL);

        assertEquals(2, result.size());
        verify(skierRepository, times(1)).findBySubscription_TypeSub(TypeSubscription.ANNUAL);
    }
}
