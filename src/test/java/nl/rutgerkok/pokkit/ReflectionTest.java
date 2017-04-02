package nl.rutgerkok.pokkit;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;

public class ReflectionTest {

	private class TestClass {
		private UUID someId = new UUID(234234, 121234);
	}

	private class TestTwoFields {
		@SuppressWarnings("unused")
		private UUID oneId = new UUID(234234, 121234);
		@SuppressWarnings("unused")
		private UUID otherId = new UUID(576, 767);
	}

	@Test(expected = NoSuchFieldError.class)
	public void testGetUndecidedValue() {
		// Two fields of same type in class - uncertain which one should be
		// retrieved
		TestTwoFields testObject = new TestTwoFields();
		Reflection.getValueInFieldOfType(testObject, UUID.class);
	}

	@Test
	public void testGetValue() {
		TestClass testObject = new TestClass();
		assertEquals(testObject.someId, Reflection.getValueInFieldOfType(testObject, UUID.class));
	}

	@Test
	public void testSetValue() {
		TestClass testObject = new TestClass();
		Reflection.setValueInFieldOfType(testObject, UUID.class, new UUID(1, 2));
		assertEquals(new UUID(1, 2), testObject.someId);
	}
}
