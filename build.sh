# сборка web-модуля
cd ./HotelAdvisors-war
mvn install
cd ../

# сборка EAR
cd ./HotelAdvisors-ear
mvn package
cd ../

cp ./HotelAdvisors-ear/target/HotelAdvisors-ear-1.0.ear .
