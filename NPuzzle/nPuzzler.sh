#!/bin/sh
java -cp bin/ solver/nPuzzler %1 %2
java -Xms=512m -Xmx=512m
