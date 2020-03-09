package radnja;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gume.AutoGuma;

public class VulkanizerskaRadnjaTest {

	private VulkanizerskaRadnja vr;
	private AutoGuma ag;
	private AutoGuma ag1;
	@Before
	public void setUp() throws Exception {
		vr=new VulkanizerskaRadnja();
		ag=new AutoGuma();
		ag1=new AutoGuma();
	}

	@After
	public void tearDown() throws Exception {
		vr=null;
		ag=null;
	}

	@Test (expected = java.lang.NullPointerException.class)
	public void testDodajGumuNull() {
		vr.dodajGumu(null);
	}
	@Test (expected = java.lang.RuntimeException.class)
	public void testDodajGumuDuplikat() {
		vr.dodajGumu(ag);
		vr.dodajGumu(ag);
		
	}

	public void testDodajGumu() {
		vr.dodajGumu(ag);
		List<AutoGuma> listaGuma=new LinkedList<>();
		assertTrue(listaGuma.size() == 1);
		assertEquals(ag,listaGuma.get(0));
		
	}
	public void testDodajGumu2() {
		vr.dodajGumu(ag);
		vr.dodajGumu(ag1);
		List<AutoGuma> listaGuma=new LinkedList<>();
		assertTrue(listaGuma.size() == 2);
		assertEquals( ag, listaGuma.get(0) );
		assertEquals( ag1, listaGuma.get(1) );
		
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void testPronadjiGumuNULL() {
		vr.pronadjiGumu(null);
	}
	@Test
	public void testPronadjiGumuIstaMarkaModel() {
		ag.setMarkaModel("Tigar");
		vr.dodajGumu(ag);
		List<AutoGuma> lista=vr.pronadjiGumu("Lav");
		assertTrue(!(lista.contains(ag)));
		
	}

	public void testPronadjiGumuIstaMarkaModelRazliciteDimenzije() {
		ag1.setMarkaModel("Trajal");
		ag.setMarkaModel("Trajal");
		ag1.setPrecnik(18);
		ag.setPrecnik(19);
		vr.dodajGumu(ag1);
		vr.dodajGumu(ag);
		List<AutoGuma> lista=vr.pronadjiGumu("Trajal");
		for (AutoGuma autoGuma : lista) {
			assertTrue((lista.contains("Trajal") && (ag.getPrecnik()!=autoGuma.getPrecnik())));
		}

	}
	
}
