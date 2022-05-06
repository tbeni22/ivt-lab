package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test2 {

  private GT4500 ship;
  private TorpedoStore mockPTS;
  private TorpedoStore mockSTS;

  @BeforeEach
  public void init(){
    mockPTS = mock(TorpedoStore.class);
    mockSTS = mock(TorpedoStore.class);
    this.ship = new GT4500(mockPTS, mockSTS);
  }

  @Test
  public void fireTorpedo_First(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(false);
    when(mockPTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPTS).fire(1);
    verify(mockSTS, times(0)).fire(anyInt());
  }

  @Test
  public void fireTorpedo_Second(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(false);
    when(mockPTS.fire(1)).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(false);
    when(mockSTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(true, result2);
    verify(mockSTS, times(1)).fire(1);
    verify(mockPTS, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_SecondaryOnly(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(true);
    when(mockPTS.fire(1)).thenReturn(false);
    when(mockSTS.isEmpty()).thenReturn(false);
    when(mockSTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockSTS, times(1)).fire(1);
    verify(mockPTS, times(0)).fire(anyInt());
  }

  @Test
  public void fireTorpedo_NoTorpedoes(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(true);
    when(mockPTS.fire(1)).thenReturn(false);
    when(mockSTS.isEmpty()).thenReturn(true);
    when(mockSTS.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockSTS, times(0)).fire(anyInt());
    verify(mockPTS, times(0)).fire(anyInt());
  }

}
