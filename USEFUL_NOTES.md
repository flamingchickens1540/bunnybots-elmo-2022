# install vendor url:
sh gradlew vendordep --url https://www.kauailabs.com/dist/frc/2022/navx_frc.json

# refresh vendor libraries: (source: https://stackoverflow.com/questions/13565082/how-can-i-force-gradle-to-redownload-dependencies)
rm -rf $HOME/.gradle/caches/
sh gradlew --refresh-dependencies #OR sh gradlew build --refresh-dependencies 
