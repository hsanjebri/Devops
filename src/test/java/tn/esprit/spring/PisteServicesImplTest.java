package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PisteServicesImplTest {

    @Mock
    private IPisteRepository pisteRepository;

    @InjectMocks
    private PisteServicesImpl pisteService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllPistes() {
        List<Piste> pistes = Arrays.asList(new Piste(), new Piste());
        when(pisteRepository.findAll()).thenReturn(pistes);

        List<Piste> result = pisteService.retrieveAllPistes();

        assertEquals(2, result.size());
        verify(pisteRepository, times(1)).findAll();
    }

    @Test
    public void testAddPiste() {
        Piste piste = new Piste();
        when(pisteRepository.save(any(Piste.class))).thenReturn(piste);

        Piste result = pisteService.addPiste(piste);

        assertEquals(piste, result);
        verify(pisteRepository, times(1)).save(piste);
    }

    @Test
    public void testRemovePiste() {
        Long numPiste = 1L;

        doNothing().when(pisteRepository).deleteById(numPiste);

        pisteService.removePiste(numPiste);

        verify(pisteRepository, times(1)).deleteById(numPiste);
    }

    @Test
    public void testRetrievePiste_Found() {
        Long numPiste = 1L;
        Piste piste = new Piste();
        when(pisteRepository.findById(numPiste)).thenReturn(Optional.of(piste));

        Piste result = pisteService.retrievePiste(numPiste);

        assertEquals(piste, result);
        verify(pisteRepository, times(1)).findById(numPiste);
    }

    @Test
    public void testRetrievePiste_NotFound() {
        Long numPiste = 1L;
        when(pisteRepository.findById(numPiste)).thenReturn(Optional.empty());

        Piste result = pisteService.retrievePiste(numPiste);

        assertNull(result);
        verify(pisteRepository, times(1)).findById(numPiste);
    }
}
