curl --output "jextract.tar.gz" "https://download.java.net/java/early_access/jextract/2/openjdk-19-jextract+2-3_linux-x64_bin.tar.gz"
tar -xf "jextract.tar.gz"
rm "jextract.tar.gz"
export PATH="$PATH:$(pwd)/jextract-19/bin"
