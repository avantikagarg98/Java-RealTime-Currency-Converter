# Currency Converter

A Java CLI application that converts currencies using live exchange rates 
from the [Frankfurter API](https://www.frankfurter.dev/).

## Features
- Live exchange rates with no API key required
- Conversion history saved locally between sessions
- Input validation and error handling

## Requirements
- Java 17+
- Maven

## How to run
1. Clone the repository
2. Run `mvn compile` then `mvn exec:java -Dexec.mainClass="BasicConverter"`

## Technologies
- Java
- Maven
- Jackson (JSON parsing)
- Frankfurter API
