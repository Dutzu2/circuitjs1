/*
    Welcome screen shown on startup for beginners (students and teachers).
    Part of the education-friendly modifications to CircuitJS1.
*/

package com.lushprojects.circuitjs1.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.lushprojects.circuitjs1.client.util.Locale;

public class WelcomeDialog extends DialogBox {

    // A simple Ohm's-law loop (battery + resistor) opened by "Open an example".
    static final String EXAMPLE_CIRCUIT =
        "$ 1 0.000005 10.20027730826997 50 5 50\n" +
        "v 224 304 224 144 0 0 40 5 0 0 0.5\n" +
        "r 224 144 432 144 0 100\n" +
        "w 432 144 432 304 0\n" +
        "w 224 304 432 304 0\n";

    final CirSim sim;

    public WelcomeDialog(CirSim sim) {
        super(false, true); // not auto-hide, modal
        this.sim = sim;
        setText(Locale.LS("Welcome!"));
        setGlassEnabled(true);

        FlowPanel body = new FlowPanel();
        body.addStyleName("welcome-dialog");
        body.addStyleName("welcome-body");

        body.add(new HTML("<h2>" + Locale.LSHTML("Circuit Simulator").asString() + "</h2>"));
        body.add(new HTML("<p>" + Locale.LSHTML("A circuit simulator for students and teachers.").asString() + "</p>"));

        body.add(new HTML("<p><b>" + Locale.LSHTML("Quick tips:").asString() + "</b></p>"));
        body.add(new HTML("<ul>" +
            "<li>" + Locale.LSHTML("Pick a part from the toolbar or the Components menu, then click on the sheet to place it.").asString() + "</li>" +
            "<li>" + Locale.LSHTML("Press the green RUN button to start the simulation.").asString() + "</li>" +
            "<li>" + Locale.LSHTML("Double-click a part to change its value.").asString() + "</li>" +
            "<li>" + Locale.LSHTML("Use the Components menu to find every part, grouped by category.").asString() + "</li>" +
            "</ul>"));

        HorizontalPanel actions = new HorizontalPanel();
        actions.setStyleName("welcome-actions");
        actions.setSpacing(0);

        Button startButton = new Button(Locale.LS("Start"));
        startButton.addStyleName("welcome-primary");
        startButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) { close(); }
        });

        Button exampleButton = new Button(Locale.LS("Open an example"));
        exampleButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                try {
                    WelcomeDialog.this.sim.importCircuitFromText(EXAMPLE_CIRCUIT, false);
                } catch (Exception e) {}
                close();
            }
        });

        actions.add(startButton);
        actions.add(exampleButton);
        body.add(actions);

        final CheckBox dontShow = new CheckBox(Locale.LS("Don't show this again"));
        dontShow.getElement().getStyle().setMarginTop(14, com.google.gwt.dom.client.Style.Unit.PX);
        dontShow.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Storage stor = Storage.getLocalStorageIfSupported();
                if (stor != null)
                    stor.setItem("hideWelcome", dontShow.getValue() ? "true" : "false");
            }
        });
        body.add(dontShow);

        setWidget(body);
        center();
        show();
    }

    public void close() {
        hide();
    }
}
