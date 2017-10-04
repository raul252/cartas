package com.raul.calculo.test;

import org.junit.Assert;
import org.junit.Test;

import com.raul.calculo.business.Baraja;


public class MyTest {
	@Test
	public void miTest()
	{
		Baraja b = new Baraja();
		
		Assert.assertEquals(21, b.getDatosAleatorios().size());
	}
}
