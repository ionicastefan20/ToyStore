#!/bin/bash
readonly JAR_FILE=out/artifacts/tema2.jar
readonly MAIN_CLASS=com.toy_store.java.Main
readonly INPUT_FILE=amazon_co-ecommerce_cleaned.csv
readonly OUTPUT_FILE=out.csv
readonly REFERENCE_FILE=amazon_co-ecommerce_cleaned.csv

readonly RED='\033[0;31m'
readonly GREEN='\033[0;32m'
readonly NC='\033[0m' # No Color

echo '========== Start Test 01 =========='
java -cp $JAR_FILE $MAIN_CLASS <<'EOF'
loadcsv amazon_co-ecommerce_cleaned.csv
savecsv out.csv
exit
EOF

DIFF=$(diff $OUTPUT_FILE $REFERENCE_FILE)
if [ "$DIFF" != "" ] 
then
    echo -e "${RED}Test failed${NC}"
    echo -e $DIFF
else
    echo -e "${GREEN}Test passed${NC}"
fi
rm $OUTPUT_FILE
echo '========== End Test 01 =========='
