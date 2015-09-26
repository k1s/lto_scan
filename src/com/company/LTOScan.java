package com.company;

import com.company.exceptions.LTONotMountedException;

import java.io.*;
import java.util.ArrayList;

public class LTOScan {

    private static String LTO = "/LTO";

    ArrayList<String> filesWalk = new ArrayList<String>();

    public ArrayList<String> getFromLTO() throws LTONotMountedException {

        File root = new File(LTO);
        File[] list = root.listFiles();
        if (list.length == 0)
            throw new LTONotMountedException();

        return walk(LTO);

    }

    public ArrayList<String> customWalk(final String PATH, final ArrayList<String> fromEDL) {

        File root = new File(PATH);
        File[] list = root.listFiles();

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                if (fromEDL.contains(String.valueOf(f.getAbsoluteFile())))
                    filesWalk.add(String.valueOf(f.getAbsoluteFile()));
            }
            if (f.isFile()) {
                if (String.valueOf(f.getAbsoluteFile()).matches(".*cine"))
                    filesWalk.add(String.valueOf(f.getAbsoluteFile()));
            }
        }

        return filesWalk;

    }

    private ArrayList<String> walk(final String PATH) {

        File root = new File(PATH);
        File[] list = root.listFiles();

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                if (String.valueOf(f.getAbsoluteFile()).matches(".*RDC"))
                    filesWalk.add(String.valueOf(f.getAbsoluteFile()));
            }
            if (f.isFile()) {
                if (String.valueOf(f.getAbsoluteFile()).matches(".*cine"))
                    filesWalk.add(String.valueOf(f.getAbsoluteFile()));
            }
        }

        return filesWalk;

    }


}
