package com.hdsoft.service;

import com.hdsoft.dao.DatabaseServer;
import com.hdsoft.model.Color;
import com.hdsoft.model.Gradient;
import com.hdsoft.utils.Dialog;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;
import org.springframework.stereotype.Component;

@Component("service")
public class Service {

    private final DatabaseServer server;
    private Parent parent;

    public Service(DatabaseServer server) {
        this.server = server;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public boolean updateGradient(Gradient gradient) {
        try {
            return server.updateGradient(gradient);
        } catch (Exception e) {
            Dialog.showError(parent, "Update Gradient Code", e.getLocalizedMessage());
        }
        return false;
    }

    public boolean updateColor(Color color) {
        try {
            return server.updateColor(color);
        } catch (Exception e) {
            Dialog.showError(parent, "Update Color Code", e.getLocalizedMessage());
        }
        return false;
    }

    public boolean saveGradient(Gradient gradient) {
        try {
            return server.saveGradient(gradient);
        } catch (Exception e) {
            Dialog.showError(parent, "Save Gradient Code", e.getLocalizedMessage());
        }
        return false;
    }

    public boolean saveColor(Color color) {
        try {
            return server.saveColor(color);
        } catch (Exception e) {
            Dialog.showError(parent, "Save Color Code", e.getLocalizedMessage());
        }
        return false;
    }

    public boolean deleteGradient(Gradient gradient) {
        try {
            return server.deleteGradient(gradient);
        } catch (Exception e) {
            Dialog.showError(parent, "Delete Gradient Code", e.getLocalizedMessage());
        }
        return false;
    }

    public boolean deleteColor(Color color) {
        try {
            return server.deleteColor(color);
        } catch (Exception e) {
            Dialog.showError(parent, "Delete Color Code", e.getLocalizedMessage());
        }
        return false;
    }

    public List<Gradient> selectGradient() {
        try {
            return server.selectGradient();
        } catch (Exception e) {
            Dialog.showError(parent, "All Gradient Codes", e.getLocalizedMessage());
        }
        return new ArrayList<>();
    }

    public List<Color> selectColor() {
        try {
            return server.selectColor();
        } catch (Exception e) {
            Dialog.showError(parent, "All Color Codes", e.getLocalizedMessage());
        }
        return new ArrayList<>();
    }

}
