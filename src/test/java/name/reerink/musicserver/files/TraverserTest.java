package name.reerink.musicserver.files;

import static org.junit.Assert.*;

import java.io.File;

import name.reerink.musicserver.misc.CallbackDefaultImpl;

import org.junit.Test;

public class TraverserTest {

	@Test
	public void testConstructNoDir() {
		try {
			new Traverser(new File("build.gradle"));
			fail("expected exception");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().startsWith("Not a folder"));
		}
	}

	@Test
	public void testConstructor() {
		Traverser traverser = new Traverser(new File("."));
		assertNotNull(traverser);
	}

	@Test
	public void testShowRoot() {
		Traverser traverser = new Traverser(new File("."));
		assertNotNull(traverser);
		traverser.showRoot();
	}

	@Test
	public void testTraverseProjectFolder() {
		Traverser traverser = new Traverser(new File("."));
		assertNotNull(traverser);
		CallbackDefaultImpl c = new CallbackDefaultImpl();
		traverser.traverse(c);
		System.out.println(c.toString());
		assertEquals(0, c.getErrors());
		assertTrue(c.getFolders() >= 40);
		assertTrue(c.getFiles() >= 20);
	}

	@Test
	public void testTraverseMusicFolder() {
		Traverser traverser = new Traverser(new File("/mnt/data/Music"));
		assertNotNull(traverser);
		CallbackDefaultImpl c = new CallbackDefaultImpl();
		traverser.traverse(c);
		System.out.println(c.toString());
		assertEquals(0, c.getErrors());
		assertTrue(c.getFolders() >= 1545);
		assertTrue(c.getFiles() >= 11379);
	}

}
