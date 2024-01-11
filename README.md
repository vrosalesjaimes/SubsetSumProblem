# SubsetSumProblem

Esta es una implementacion de Búsqueda Tabu para aproximar soluciones para SSP. El sistema esta construido con java 17 y hace uso de maven.

## Compilación 
Desde línea de comandos y haciendo uso de maven 

```bash
mvn install
```

# Ejecución

El sistema recibe dos parametros:
- \<Archivo\>: archivo con numeros separados por comas, el ultimo entero es el target o número objetivo
- \<Semilla\>: numero entero 

Se ejecuta de la siguiente forma:
```bash
java -jar target/*.jar <Archivo> <Semilla>
```

Por ejemplo

```bash
java -jar target/SubsetSumProblem-jar-with-dependencies.jar inputs/instancia1.ssp 57 
java -jar target/SubsetSumProblem-jar-with-dependencies.jar inputs/instancia2.ssp 4 
```
