./gradlew jextract
rm -r jolt-java-headers/src/main/java/jolt/*
cp -r jolt-java-headers/build/generated/sources/jextract/main/java/jolt/* jolt-java-headers/src/main/java/jolt
./gradlew :jolt-java-headers:clean
