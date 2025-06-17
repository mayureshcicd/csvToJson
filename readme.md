# CSV to JSON Processor Demo

This repository consists of two separate projects:

- **csvtojson-util**: A utility module that provides functionality to convert any `.csv` file into a JSON string.
- **csv-to-json-processor-demo**: A Spring Boot application exposing a REST API endpoint to upload `.csv` files and receive the corresponding JSON string in response.

---

## 🔧 Project Structure

1. **csvtojson-util**  
   - A reusable utility library.
   - Converts `.csv` content into a JSON string.
   - Can be integrated with other applications.

2. **csv-to-json-processor-demo**  
   - A Spring Boot REST API application.
   - Provides a `/json-converter` endpoint.
   - Accepts `.csv` file upload and returns the JSON string as the response.

---

## 🚀 Getting Started

Follow the steps below to set up and run the projects locally:

### Step 1: Clone Both Repositories

Download or clone both the projects to your local environment:

- `csvtojson-util`
- `csv-to-json-processor-demo`

### Step 2: Build `csvtojson-util`

Navigate to the `csvtojson-util` project directory and run the following commands:

```bash
mvn clean install
mvn eclipse:eclipse   # (Optional - only if you're using Eclipse)

