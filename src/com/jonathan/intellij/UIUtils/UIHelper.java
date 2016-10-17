package com.jonathan.intellij.UIUtils;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;

import java.awt.*;

/**
 * Created by JianYang on 10/17/16.
 */
public class UIHelper {

    public static void showPopupBallon(final Editor editor, final String result) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                JBPopupFactory factory = JBPopupFactory.getInstance();
                factory.createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(186, 238, 186), new Color(73, 117, 73)), null)
                        .setFadeoutTime(5000)
                        .createBalloon()
                        .show(factory.guessBestPopupLocation(editor), Balloon.Position.below);
            }
        });
    }

    public static void showMessageBox(String result) {
        Messages.showMessageDialog(
                result,
                "Translation Result:",
                Messages.getInformationIcon()
        );
    }
}
