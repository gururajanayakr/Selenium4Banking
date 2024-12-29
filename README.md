# Selenium4Banking is a Maven BDD Cucumber Framework with Page Object Model (POM) implementation
This project demonstrates a **Selenium test automation framework** built using:
- **Maven** for build and dependency management.
- **Cucumber** for Behavior-Driven Development (BDD).
- **Page Object Model (POM)** for structured and maintainable code.
- **Allure Report** for reporing the tests.

## Features
- Easy-to-maintain test scripts using the POM design pattern.
- Supports multiple browsers (Chrome, Firefox, etc.).
- Simple configuration via `config.properties`.
- Test data in data.json file and Excel sheets
- Detailed test reports using Cucumber Reports.
- Parallel test execution support.

---

## Prerequisites
1. **Java** (JDK 17 or higher) installed and configured in `PATH`.
2. **Maven** installed and configured in `PATH`.
3. A compatible browser (e.g., Chrome, Firefox).
4. ChromeDriver or GeckoDriver matching your browser version.

---

## Project Structure

src ├── main │   ├── java │   │   └── com.example.pages   # Page Object Model classes │   └── resources │       └── config.properties   # Configuration file ├── test │   ├── java │   │   └── com.example.steps   # Step definition classes │   └── resources │       └── features            # Cucumber feature files

---

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/gururajanayakr/Selenium4Banking.git
   cd Selenium4Banking

2. Install dependencies:

mvn clean install


3. Update config.properties file with your desired configurations:

browser=chrome
baseUrl=https://example.com


4. Ensure the appropriate WebDriver (e.g., chromedriver.exe) is placed in your system's PATH or configured in config.properties.




---

Running Tests

Single Test Execution

Run a specific feature file:

mvn test -Dcucumber.options="src/test/resources/features/your-feature-file.feature"

Full Suite Execution

Run all tests:

mvn clean test

Parallel Execution

To execute tests in parallel, configure the pom.xml with the required parallel execution settings.


---

Test Reports

After execution, detailed HTML reports are generated at:

target/cucumber-reports/index.html


---

Technologies Used

Selenium WebDriver: Browser automation.

Cucumber: BDD framework.

Maven: Build tool and dependency manager.

Page Object Model (POM): Design pattern for test automation.



---

Contributing

Feel free to fork this repository, make your changes, and submit a pull request. Contributions are always welcome!


---

### Key Points:
1. Replace placeholders like `your-username`, `your-repository`, and `your-feature-file.feature` with actual details from your project.
2. Add additional sections as needed (e.g., FAQs, troubleshooting, etc.).
3. Keep it concise yet informative, ensuring anyone can understand and use your project easily.
