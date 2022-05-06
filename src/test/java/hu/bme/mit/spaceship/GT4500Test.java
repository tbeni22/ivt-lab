package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTS;

  @BeforeEach
  public void init(){
    mockTS = mock(TorpedoStore.class);
    when(mockTS.isEmpty()).thenReturn(false);
    when(mockTS.fire(1)).thenReturn(true);
    //TorpedoStore mockSTS = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTS, mockTS);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    verify(mockTS).fire(1);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    verify(mockTS, times(2)).fire(1);

    // Assert
    assertEquals(true, result);
  }

}
