package cate.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void storage_fileNotFound_emtpyArray(){
        Storage storage = new Storage("file.txt");
        assertEquals(storage.load(), new ArrayList<>());
    }
}
