package utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

class FileUtilsTest {

	@Test
	void testLogToFile() {
		Boolean expected = true;
		Boolean result = FileUtils.logToFile("Junit test.");
		assertEquals(result, expected);
	}
	
	@Test
	void testLogToFileSecond() {
		Boolean expected = true;
		Boolean result = FileUtils.logToFile("Junit test.", false);
		assertEquals(result, expected);
	}
	
	@Test
	void testLogToFileThird() {
		Boolean expected = true;
		Boolean result = FileUtils.logToFile("Junit test.", false);
		assertEquals(result, expected);
	}

	@Test
	void testClearDirectory() {
		Boolean expected = false;
		Boolean result = FileUtils.clearDirectory(new File("test_dir"));
		assertEquals(result, expected);
//		fail("Not yet implemented");
	}

	@Test
	void testRemoveDirectory() {
		Boolean expected = false;
		Boolean result = FileUtils.removeDirectory(new File("test_dir"));
		assertEquals(result, expected);
	}

}
