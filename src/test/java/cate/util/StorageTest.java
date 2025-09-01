package cate.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void storage_fileNotFound_emtpyArray() {
        Storage storage = new Storage("file.txt");
        assertEquals(storage.load(), new ArrayList<>());
    }
}
