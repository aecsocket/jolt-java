$BuildDir=$args[0]
$BuildType=$args[1]
$MakeWorkers=$args[2]

cmake -S . -B $BuildDir -G "Unix Makefiles" -DCMAKE_BUILD_TYPE=$BuildType -DCMAKE_CXX_COMPILER=g++
make -C $BuildDir -j $MakeWorkers

