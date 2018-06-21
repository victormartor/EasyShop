package victor.easyshop.activities;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import victor.easyshop.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestCompletoUI {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testCompletoUI() {
        ViewInteraction editText = onView(
                allOf(withId(R.id.direccionIP_1oct),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        editText.perform(click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.direccionIP_1oct),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        editText2.perform(replaceText("192"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.direccionIP_1oct), withText("192"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        editText3.perform(pressImeActionButton());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.direccionIP_2oct),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        editText4.perform(replaceText("168"), closeSoftKeyboard());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.direccionIP_2oct), withText("168"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        editText5.perform(pressImeActionButton());

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.direccionIP_3oct),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                4),
                        isDisplayed()));
        editText6.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.direccionIP_3oct), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                4),
                        isDisplayed()));
        editText7.perform(pressImeActionButton());

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.direccionIP_4oct),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                6),
                        isDisplayed()));
        editText8.perform(replaceText("6"), closeSoftKeyboard());

        ViewInteraction editText9 = onView(
                allOf(withId(R.id.direccionIP_4oct), withText("6"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                6),
                        isDisplayed()));
        editText9.perform(pressImeActionButton());

        ViewInteraction editText10 = onView(
                allOf(withId(R.id.direccionIP_1oct), withText("192"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        editText10.check(matches(withText("192")));

        ViewInteraction textView = onView(
                allOf(withText("."),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText(".")));

        ViewInteraction editText11 = onView(
                allOf(withId(R.id.direccionIP_2oct), withText("168"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        editText11.check(matches(withText("168")));

        ViewInteraction textView2 = onView(
                allOf(withText("."),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView2.check(matches(withText(".")));

        ViewInteraction editText12 = onView(
                allOf(withId(R.id.direccionIP_3oct), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                4),
                        isDisplayed()));
        editText12.check(matches(withText("1")));

        ViewInteraction textView3 = onView(
                allOf(withText("."),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                5),
                        isDisplayed()));
        textView3.check(matches(withText(".")));

        ViewInteraction editText13 = onView(
                allOf(withId(R.id.direccionIP_4oct), withText("6"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                6),
                        isDisplayed()));
        editText13.check(matches(withText("6")));

        ViewInteraction button = onView(
                allOf(withId(R.id.boton_confirmar),
                        childAtPosition(
                                allOf(withId(R.id.layout_elementos_splash),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.boton_confirmar), withText("Conectar"),
                        childAtPosition(
                                allOf(withId(R.id.layout_elementos_splash),
                                        childAtPosition(
                                                withClassName(is("android.widget.FrameLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        button2.perform(click());

        ViewInteraction gridView = onView(
                allOf(withId(R.id.grid),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        gridView.check(matches(isDisplayed()));

        DataInteraction frameLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.grid),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        frameLayout.perform(click());

        ViewInteraction imageView = onView(
                allOf(withId(R.id.imageViewMarca),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.mi_toolbar),
                                        1),
                                1),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction gridView2 = onView(
                allOf(withId(R.id.grid),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        gridView2.check(matches(isDisplayed()));

        DataInteraction frameLayout2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.grid),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        frameLayout2.perform(click());

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.imageViewMarca),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.mi_toolbar),
                                        1),
                                1),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction gridView3 = onView(
                allOf(withId(R.id.grid),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        gridView3.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.texto), withText("9,95 €"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.grid),
                                        0),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("9,95 €")));

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button2), withText("Cancelar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        appCompatButton.perform(scrollTo(), click());

        DataInteraction frameLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.grid),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        frameLayout3.perform(click());

        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.imageViewMarca),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.mi_toolbar),
                                        1),
                                1),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        ViewInteraction imageView4 = onView(
                allOf(withId(R.id.imagen_extendida),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        imageView4.check(matches(isDisplayed()));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.lista_circulos),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.precio_articulo), withText("9,95 €"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                2),
                        isDisplayed()));
        textView5.check(matches(withText("9,95 €")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.marca), withText("ZARA"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView6.check(matches(withText("ZARA")));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.lista_colores),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        recyclerView2.check(matches(isDisplayed()));

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.lista_tallas),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        recyclerView3.check(matches(isDisplayed()));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.mas_info_tallas), withText("+info"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView7.check(matches(withText("+info")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.texto_talla), withText("S"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView8.check(matches(withText("S")));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button2), withText("Cancelar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button2), withText("Cancelar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.boton_der),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.datos_articulo),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.boton_der),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.datos_articulo),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.boton_der),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.datos_articulo),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageView3.perform(click());

        ViewInteraction appCompatImageView4 = onView(
                allOf(withId(R.id.imagen_extendida),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatImageView4.perform(click());

        ViewInteraction imageView5 = onView(
                allOf(withId(R.id.imagen_extendida),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        imageView5.check(matches(isDisplayed()));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.lista_colores),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView4.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction recyclerView5 = onView(
                allOf(withId(R.id.lista_colores),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView5.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction recyclerView6 = onView(
                allOf(withId(R.id.lista_tallas),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView6.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction appCompatImageView5 = onView(
                allOf(withId(R.id.boton_der),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.datos_articulo),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageView5.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.button), withText("Añadir al carrito"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(android.R.id.button1), withText("Confirmar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction imageView6 = onView(
                allOf(withId(R.id.imageViewMarca),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.mi_toolbar),
                                        1),
                                1),
                        isDisplayed()));
        imageView6.check(matches(isDisplayed()));

        ViewInteraction recyclerView7 = onView(
                allOf(withId(R.id.lista_combinaciones),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        recyclerView7.check(matches(isDisplayed()));

        ViewInteraction textView9 = onView(
                allOf(withText("También puede interesarle:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView9.check(matches(withText("También puede interesarle:")));

        ViewInteraction recyclerView8 = onView(
                allOf(withId(R.id.lista_combinaciones),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView8.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.boton_mas), withText("+"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lista_tallas),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.boton_mas), withText("+"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lista_tallas),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.button), withText("Añadir al carrito"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(android.R.id.button1), withText("Confirmar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton9.perform(scrollTo(), click());

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.numero_art_carrito), withText("2"),
                        childAtPosition(
                                allOf(withId(R.id.fondo_carrito),
                                        childAtPosition(
                                                withId(R.id.mi_toolbar),
                                                3)),
                                1),
                        isDisplayed()));
        textView10.check(matches(withText("2")));

        ViewInteraction appCompatImageView6 = onView(
                allOf(withId(R.id.imagenCarrito),
                        childAtPosition(
                                allOf(withId(R.id.fondo_carrito),
                                        childAtPosition(
                                                withId(R.id.mi_toolbar),
                                                3)),
                                0),
                        isDisplayed()));
        appCompatImageView6.perform(click());

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.numero_art_carrito), withText("2"),
                        childAtPosition(
                                allOf(withId(R.id.fondo_carrito),
                                        childAtPosition(
                                                withId(R.id.mi_toolbar),
                                                3)),
                                1),
                        isDisplayed()));
        textView11.check(matches(withText("2")));

        ViewInteraction gridView4 = onView(
                allOf(withId(R.id.grid),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        gridView4.check(matches(isDisplayed()));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.marca), withText("ZARA"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView12.check(matches(withText("ZARA")));

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.descripcion), withText("CAMISETA TEXTO ESTAMPADO\nTalla L\nColor Naranja"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView13.check(matches(withText("CAMISETA TEXTO ESTAMPADO Talla L Color Naranja")));

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.precio_item_carro), withText("9,95 €"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        textView14.check(matches(withText("9,95 €")));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.cabeceraTicket), withText("Artículos"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView15.check(matches(withText("Artículos")));

        ViewInteraction textView16 = onView(
                allOf(withId(R.id.carrito), withText("1-3-5\n\n4-6-21\n\n"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView16.check(matches(withText("1-3-5  4-6-21  ")));

        ViewInteraction textView17 = onView(
                allOf(withId(R.id.carritoPrecios), withText("9,95 €\n\n25,95 €\n\n"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                1),
                        isDisplayed()));
        textView17.check(matches(withText("9,95 €  25,95 €  ")));

        ViewInteraction textView18 = onView(
                allOf(withId(R.id.total), withText("Total"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                0),
                        isDisplayed()));
        textView18.check(matches(withText("Total")));

        ViewInteraction textView19 = onView(
                allOf(withId(R.id.totalPrecio), withText("35,90 €"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                1),
                        isDisplayed()));
        textView19.check(matches(withText("35,90 €")));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.button_comprar),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                3),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(android.R.id.button2), withText("Cancelar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        appCompatButton10.perform(scrollTo(), click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.grid),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(1);
        linearLayout.perform(click());

        ViewInteraction appCompatImageView7 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                2),
                        1),
                        isDisplayed()));
        appCompatImageView7.perform(click());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(android.R.id.button1), withText("Confirmar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton11.perform(scrollTo(), click());

        ViewInteraction textView20 = onView(
                allOf(withId(R.id.numero_art_carrito), withText("1"),
                        childAtPosition(
                                allOf(withId(R.id.fondo_carrito),
                                        childAtPosition(
                                                withId(R.id.mi_toolbar),
                                                3)),
                                1),
                        isDisplayed()));
        textView20.check(matches(withText("1")));

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.button_comprar), withText("Confirmar"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        appCompatButton12.perform(click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(android.R.id.button1), withText("Confirmar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton13.perform(scrollTo(), click());

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(android.R.id.button1), withText("Aceptar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton14.perform(scrollTo(), click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
