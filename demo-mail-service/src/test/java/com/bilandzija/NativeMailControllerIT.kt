package com.bilandzija

import io.quarkus.test.junit.NativeImageTest

@NativeImageTest
class NativeMailControllerIT : MailControllerTest() { // Execute the same tests but in native mode.
}