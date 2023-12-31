#!/bin/bash

# Nombre del archivo JAR y la entrada
JAR_FILE="target/SubsetSumProblem-jar-with-dependencies.jar"
INPUT_FILE="inputs/instancia1.ssp"

# Rango de semillas que deseas probar
SEMILLA_INICIAL=1
SEMILLA_FINAL=100

# Nombre del archivo de salida
OUTPUT_FILE="salida.csv"

# Función para ejecutar el comando con timeout y registro
execute_with_timeout() {
    semilla="$1"
    comando="java -jar \"$JAR_FILE\" \"$INPUT_FILE\" \"$semilla\""
    timeout 3m java -jar "$JAR_FILE" "$INPUT_FILE" "$semilla" 2>&1 | tee -a "$OUTPUT_FILE"
    echo "-------------------------------------------------------------------------------------"
}

# Bucle for para ejecutar el comando con diferentes semillas
for ((semilla = SEMILLA_INICIAL; semilla <= SEMILLA_FINAL; semilla++))
do
    execute_with_timeout "$semilla"
done

echo "Proceso completo. Los resultados se han guardado en $OUTPUT_FILE"