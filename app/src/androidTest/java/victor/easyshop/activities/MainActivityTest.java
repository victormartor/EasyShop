package victor.easyshop.activities;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() {
        //MainActivity instance = new MainActivity();
        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, MainActivity.class);
        appContext.startActivity(intent);

    }

    @Test
    public void confirmar() {
    }
}