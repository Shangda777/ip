#!/bin/bash
# Run Richal from the project root directory
cd "$(dirname "$0")"

# Compile if needed
if [ ! -f "src/main/java/richal/Richal.class" ]; then
    echo "Compiling..."
    cd src/main/java
    javac richal/*.java
    cd ../..
fi

# Run
java -cp src/main/java richal.Richal
