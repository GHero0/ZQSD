# Makefile for Java Project
# This Makefile compiles all Java files in the src directory (including subdirectories),
# places the compiled .class files directly in the bin directory,
# and generates documentation in the docs directory.

# Directories
SRC_DIR = src
BIN_DIR = bin
ASSETS_DIR = assets
DOCS_DIR = docs

# Java compiler
JAVAC = javac
JAVADOC = javadoc
JAVA = java

# Source files and class files

SRC_FILES = $(shell find $(SRC_DIR) -name "*.java")
CLASS_FILES = $(patsubst $(SRC_DIR)/%.java, $(BIN_DIR)/%.class, $(SRC_FILES))

# Main class to run (update with the correct package structure if applicable)
MAIN_CLASS = main.Mainclass

# Default target
.PHONY: all
all: compile

# Compile all Java files
compile: $(SRC_FILES)
	@echo "Compiling all Java files to $(BIN_DIR)"
	@mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $(SRC_FILES)

# Generate documentation
.PHONY: docs
docs:
	@echo "Generating documentation in $(DOCS_DIR)"
	@mkdir -p $(DOCS_DIR)
	$(JAVADOC) -d $(DOCS_DIR) $(SRC_FILES)

# Run the main class
.PHONY: run
run: run-windows

.PHONY: run-windows
run-windows: compile
	@echo "Running $(MAIN_CLASS)"
	powershell.exe -Command "& { java -cp '$(BIN_DIR);$(ASSETS_DIR)' $(MAIN_CLASS) }"

.PHONY: run-linux
run-linux: compile
	@echo "Running $(MAIN_CLASS)"
	java -cp '$(BIN_DIR):$(ASSETS_DIR)' $(MAIN_CLASS)


# Clean up generated files
.PHONY: clean
clean:
	@echo "Cleaning up generated files"
	@rm -rf $(BIN_DIR) $(DOCS_DIR)

# Beautify output
.PHONY: help
help:
	@echo "Makefile for Java Project"
	@echo "Targets:"
	@echo "  all      - Compile and generate documentation"
	@echo "  compile  - Compile all Java files into the bin directory"
	@echo "  docs     - Generate Javadoc documentation in the docs directory"
	@echo "  run      - Run the main class"
	@echo "  clean    - Remove all generated files"

