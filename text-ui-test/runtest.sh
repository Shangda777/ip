#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile ALL java files into ../bin
if ! javac -Xlint:none -d ../bin $(find ../src/main/java -name "*.java")
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program 
java -classpath ../bin richal.Richal < input.txt > ACTUAL.TXT

# convert to UNIX format 
cp EXPECTED.TXT EXPECTED-UNIX.TXT 2>/dev/null
if command -v dos2unix >/dev/null 2>&1; then
  dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT >/dev/null 2>&1
else
  # fallback
  tr -d '\r' < EXPECTED.TXT > EXPECTED-UNIX.TXT
  tr -d '\r' < ACTUAL.TXT > ACTUAL.TXT.tmp && mv ACTUAL.TXT.tmp ACTUAL.TXT
fi

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
