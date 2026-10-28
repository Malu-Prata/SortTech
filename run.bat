@echo off
chcp 65001 > nul
echo === SortTech ===

where javac >nul 2>nul
if %errorlevel% neq 0 (
    echo [ERRO] O compilador javac nao foi encontrado no PATH.
    echo Por favor instale o JDK (Java Development Kit) em: https://www.oracle.com/java/technologies/downloads/ ou https://adoptium.net/
    pause
    exit /b 1
)

echo [1/2] Compilando arquivos Java...
if not exist bin mkdir bin
dir /s /b src\*.java > sources.txt
javac -encoding UTF-8 -d bin @sources.txt
del sources.txt

echo [2/2] Executando aplicacao...
java -cp bin com.estudotempo.Main
pause
