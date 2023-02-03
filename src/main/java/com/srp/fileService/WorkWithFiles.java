package com.srp.fileService;


import com.srp.domains.FileType;

import java.util.function.Supplier;

public class WorkWithFiles {
    public static FileType findByName( String name) {
        String aa = "123";
        Supplier<Integer> c = aa :: length;

        for ( FileType fileType : FileType.values() ) {
            if ( fileType.name().equalsIgnoreCase(name) )
                return fileType;
        }
        return FileType.JSON;
    }

}
