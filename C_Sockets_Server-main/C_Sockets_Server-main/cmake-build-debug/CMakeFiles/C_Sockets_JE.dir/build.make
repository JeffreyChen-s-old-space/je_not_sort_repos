# CMAKE generated file: DO NOT EDIT!
# Generated by "NMake Makefiles" Generator, CMake Version 3.16

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

!IF "$(OS)" == "Windows_NT"
NULL=
!ELSE
NULL=nul
!ENDIF
SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\JetBrainIDE\Clion\CLion 2020.1.2\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\JetBrainIDE\Clion\CLion 2020.1.2\bin\cmake\win\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server\cmake-build-debug"

# Include any dependencies generated for this target.
include CMakeFiles\C_Sockets_JE.dir\depend.make

# Include the progress variables for this target.
include CMakeFiles\C_Sockets_JE.dir\progress.make

# Include the compile flags for this target's objects.
include CMakeFiles\C_Sockets_JE.dir\flags.make

CMakeFiles\C_Sockets_JE.dir\Socket_Server.cpp.obj: CMakeFiles\C_Sockets_JE.dir\flags.make
CMakeFiles\C_Sockets_JE.dir\Socket_Server.cpp.obj: ..\Socket_Server.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/C_Sockets_JE.dir/Socket_Server.cpp.obj"
	C:\PROGRA~2\MICROS~2\2017\PROFES~1\VC\Tools\MSVC\1416~1.270\bin\Hostx86\x86\cl.exe @<<
 /nologo /TP $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) /FoCMakeFiles\C_Sockets_JE.dir\Socket_Server.cpp.obj /FdCMakeFiles\C_Sockets_JE.dir\ /FS -c "D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server\Socket_Server.cpp"
<<

CMakeFiles\C_Sockets_JE.dir\Socket_Server.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/C_Sockets_JE.dir/Socket_Server.cpp.i"
	C:\PROGRA~2\MICROS~2\2017\PROFES~1\VC\Tools\MSVC\1416~1.270\bin\Hostx86\x86\cl.exe > CMakeFiles\C_Sockets_JE.dir\Socket_Server.cpp.i @<<
 /nologo /TP $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server\Socket_Server.cpp"
<<

CMakeFiles\C_Sockets_JE.dir\Socket_Server.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/C_Sockets_JE.dir/Socket_Server.cpp.s"
	C:\PROGRA~2\MICROS~2\2017\PROFES~1\VC\Tools\MSVC\1416~1.270\bin\Hostx86\x86\cl.exe @<<
 /nologo /TP $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) /FoNUL /FAs /FaCMakeFiles\C_Sockets_JE.dir\Socket_Server.cpp.s /c "D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server\Socket_Server.cpp"
<<

CMakeFiles\C_Sockets_JE.dir\Socket_Client.cpp.obj: CMakeFiles\C_Sockets_JE.dir\flags.make
CMakeFiles\C_Sockets_JE.dir\Socket_Client.cpp.obj: ..\Socket_Client.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/C_Sockets_JE.dir/Socket_Client.cpp.obj"
	C:\PROGRA~2\MICROS~2\2017\PROFES~1\VC\Tools\MSVC\1416~1.270\bin\Hostx86\x86\cl.exe @<<
 /nologo /TP $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) /FoCMakeFiles\C_Sockets_JE.dir\Socket_Client.cpp.obj /FdCMakeFiles\C_Sockets_JE.dir\ /FS -c "D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server\Socket_Client.cpp"
<<

CMakeFiles\C_Sockets_JE.dir\Socket_Client.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/C_Sockets_JE.dir/Socket_Client.cpp.i"
	C:\PROGRA~2\MICROS~2\2017\PROFES~1\VC\Tools\MSVC\1416~1.270\bin\Hostx86\x86\cl.exe > CMakeFiles\C_Sockets_JE.dir\Socket_Client.cpp.i @<<
 /nologo /TP $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server\Socket_Client.cpp"
<<

CMakeFiles\C_Sockets_JE.dir\Socket_Client.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/C_Sockets_JE.dir/Socket_Client.cpp.s"
	C:\PROGRA~2\MICROS~2\2017\PROFES~1\VC\Tools\MSVC\1416~1.270\bin\Hostx86\x86\cl.exe @<<
 /nologo /TP $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) /FoNUL /FAs /FaCMakeFiles\C_Sockets_JE.dir\Socket_Client.cpp.s /c "D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server\Socket_Client.cpp"
<<

# Object files for target C_Sockets_JE
C_Sockets_JE_OBJECTS = \
"CMakeFiles\C_Sockets_JE.dir\Socket_Server.cpp.obj" \
"CMakeFiles\C_Sockets_JE.dir\Socket_Client.cpp.obj"

# External object files for target C_Sockets_JE
C_Sockets_JE_EXTERNAL_OBJECTS =

C_Sockets_JE.exe: CMakeFiles\C_Sockets_JE.dir\Socket_Server.cpp.obj
C_Sockets_JE.exe: CMakeFiles\C_Sockets_JE.dir\Socket_Client.cpp.obj
C_Sockets_JE.exe: CMakeFiles\C_Sockets_JE.dir\build.make
C_Sockets_JE.exe: CMakeFiles\C_Sockets_JE.dir\objects1.rsp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir="D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_3) "Linking CXX executable C_Sockets_JE.exe"
	"C:\JetBrainIDE\Clion\CLion 2020.1.2\bin\cmake\win\bin\cmake.exe" -E vs_link_exe --intdir=CMakeFiles\C_Sockets_JE.dir --rc=C:\PROGRA~2\WI3CF2~1\10\bin\100190~1.0\x86\rc.exe --mt=C:\PROGRA~2\WI3CF2~1\10\bin\100190~1.0\x86\mt.exe --manifests  -- C:\PROGRA~2\MICROS~2\2017\PROFES~1\VC\Tools\MSVC\1416~1.270\bin\Hostx86\x86\link.exe /nologo @CMakeFiles\C_Sockets_JE.dir\objects1.rsp @<<
 /out:C_Sockets_JE.exe /implib:C_Sockets_JE.lib /pdb:"D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server\cmake-build-debug\C_Sockets_JE.pdb" /version:0.0  /machine:X86 /debug /INCREMENTAL /subsystem:console  kernel32.lib user32.lib gdi32.lib winspool.lib shell32.lib ole32.lib oleaut32.lib uuid.lib comdlg32.lib advapi32.lib 
<<

# Rule to build all files generated by this target.
CMakeFiles\C_Sockets_JE.dir\build: C_Sockets_JE.exe

.PHONY : CMakeFiles\C_Sockets_JE.dir\build

CMakeFiles\C_Sockets_JE.dir\clean:
	$(CMAKE_COMMAND) -P CMakeFiles\C_Sockets_JE.dir\cmake_clean.cmake
.PHONY : CMakeFiles\C_Sockets_JE.dir\clean

CMakeFiles\C_Sockets_JE.dir\depend:
	$(CMAKE_COMMAND) -E cmake_depends "NMake Makefiles" "D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server" "D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server" "D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server\cmake-build-debug" "D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server\cmake-build-debug" "D:\WorkSpaces\Program WorkSpace\C\Projects\C_Sockets_Server\cmake-build-debug\CMakeFiles\C_Sockets_JE.dir\DependInfo.cmake" --color=$(COLOR)
.PHONY : CMakeFiles\C_Sockets_JE.dir\depend

