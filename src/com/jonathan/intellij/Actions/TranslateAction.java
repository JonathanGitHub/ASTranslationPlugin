package com.jonathan.intellij.Actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.jonathan.intellij.API.TranslationAPI;
import org.apache.http.util.TextUtils;

import java.net.URISyntaxException;


/**
 * Created by JIANYANG on 10/14/16.
 */
public class TranslateAction extends AnAction {


    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        final Editor mEditor = e.getData(PlatformDataKeys.EDITOR);
        if (null == mEditor) {
            return;
        }
        SelectionModel model = mEditor.getSelectionModel();
        final String selectedText = model.getSelectedText();
        if (TextUtils.isEmpty(selectedText)) {
            return;
        }

        try {
            TranslationAPI.getTranslationOnBalloon(mEditor, selectedText);
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }
}



