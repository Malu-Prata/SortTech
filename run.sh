#!/usr/bin/env bash
set -e

echo "=== SortTech ==="

if ! command -v javac &> /dev/null || ! command -v java &> /dev/null; then
    echo "Erro: Java Development Kit (JDK) não encontrado no seu sistema."
    echo "Para instalar no Ubuntu/Debian execute: sudo apt update && sudo apt install default-jdk"
    echo "Para Fedora execute: sudo dnf install java-latest-openjdk-devel"
    echo "Para Arch execute: sudo pacman -S jdk-openjdk"
    exit 1
fi

echo "Compilando arquivos Java..."
mkdir -p bin
javac -encoding UTF-8 -d bin $(find src -name "*.java")

echo "Iniciando aplicação..."
java -cp bin com.estudotempo.Main
