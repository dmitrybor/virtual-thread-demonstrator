# install jdk
wget https://download.java.net/java/GA/jdk19.0.2/fdb695a9d9064ad6b064dc6df578380c/7/GPL/openjdk-19.0.2_linux-x64_bin.tar.gz -O openjdk19.tar.gz
mkdir openjdk19
tar -xf openjdk19.tar.gz -C openjdk19 --strip-components 1

mkdir -p /usr/lib/jvm
mv openjdk19 /usr/lib/jvm/openjdk19

# Add service user
useradd -M vtd
usermod -L vtd
