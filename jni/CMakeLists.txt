cmake_minimum_required(VERSION 3.5)
set(CMAKE_C_COMPILER gcc)
set(CMAKE_CXX_COMPILER g++)
enable_language(C)
enable_language(CXX)
project(Jdlib)

# set(CMAKE_MACOSX_RPATH 1)
set (CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fPIC")
message(STATUS "这是一条日志信息")

set(CMAKE_BUILD_TYPE Release)

find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})

message(STATUS "这是一条日志信息")

set(DLib_LOCATION ${CMAKE_CURRENT_SOURCE_DIR}/build/dlib/)
#execute_process (
 #   COMMAND bash -c "git clone https://github.com/davisking/dlib.git"
#)
message(STATUS "这是一条日志信息")
add_subdirectory(${DLib_LOCATION} dlib_build)


add_library(Jdlib SHARED Jdlib.cpp utils.cpp handlers.cpp)
target_link_libraries(Jdlib  dlib::dlib )


if(APPLE)
set_target_properties(Jdlib PROPERTIES LIBRARY_OUTPUT_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/../Jdlib/src/main/resources/native/macosx)
endif()

if(UNIX AND NOT APPLE)
set_target_properties(Jdlib PROPERTIES LIBRARY_OUTPUT_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/../Jdlib/src/main/resources/native/linux)
endif()

if(WIN32)
set_target_properties(Jdlib PROPERTIES LIBRARY_OUTPUT_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/../Jdlib/src/main/resources/native/win)
endif()