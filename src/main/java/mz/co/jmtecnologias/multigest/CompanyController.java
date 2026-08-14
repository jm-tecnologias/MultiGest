/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mz.co.jmtecnologias.multigest;

import java.io.IOException;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author JM-Tecnologias
 */
public class CompanyController {

    @FXML private TableView<Company> table;
    @FXML private TableColumn<Company, Integer> idColumn;
    @FXML private TableColumn<Company, String> nameColumn;
    @FXML private TableColumn<Company, String> resonColumn;
    @FXML private TableColumn<Company, Integer> nuitColumn;
    @FXML private TableColumn<Company, Boolean> deletedStatusColumn;
    @FXML private TableColumn<Company, String> createdAtColumn;
    @FXML private TableColumn<Company, String> updatedATColumn;
    
    @FXML private TextField idText;
    @FXML private TextField nameText;
    @FXML private TextField resonText;
    @FXML private TextField nuitText;
    @FXML private TextField searchText;
    @FXML private TextArea descriptionTextArea;

    @FXML
    private Label statusLabel;

    private ObservableList<Company> companies;
    private Service service;

    @FXML
    public void initialize() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        service = new Service();
        loadCompanies();

        

        companies = FXCollections.observableArrayList();

        table.setItems(companies);

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        resonColumn.setCellValueFactory(
                new PropertyValueFactory<>("reson")
        );
        nuitColumn.setCellValueFactory(
                new PropertyValueFactory<>("nuit")
        );
        createdAtColumn.setCellValueFactory(
                new PropertyValueFactory<>("created_at")
        );
        updatedATColumn.setCellValueFactory(
                new PropertyValueFactory<>("updated_at")
        );
        deletedStatusColumn.setCellValueFactory(
                new PropertyValueFactory<>("deleted_status")
        );
    }
    
    @FXML
    void actionChecker() throws IOException, InterruptedException{
        if(!idText.getText().equals("")){
            updateCompany();
        }else{
            createCompany();
        }
    }
    
    private void createCompany() throws IOException, InterruptedException{
        Company company = service.createCompany(new Company(nameText.getText(), 
                resonText.getText(), Integer.parseInt(nuitText.getText()),
                descriptionTextArea.getText()));
        if (company instanceof Company){
            loadCompanies();
            resetField();
        }
    }
    
    private void updateCompany() throws IOException, InterruptedException{
        Company company = service.updateCompany(Integer.parseInt(idText.getText()), new Company(nameText.getText(), 
                resonText.getText(), Integer.parseInt(nuitText.getText()),
                descriptionTextArea.getText()));
        if (company instanceof Company){
            loadCompanies();
            resetField();
        }
    }
    
    @FXML
    void selectedRow(){
        Company x = table.getSelectionModel().getSelectedItem();
        if(x != null){
            idText.setText(String.valueOf(x.getId()));
            nameText.setText(x.getName());
            resonText.setText(x.getReson());
            nuitText.setText(String.valueOf(x.getNuit()));
            descriptionTextArea.setText(x.getDescription());
        }
    }
    
    @FXML
    private void resetField(){
        idText.setText("");
        nameText.setText("");
        resonText.setText("");
        nuitText.setText("");
        descriptionTextArea.setText("");
    }

    @FXML
    private void loadCompanies() {

        statusLabel.setText("A carregar...");

        Thread thread = new Thread(() -> {

            try {

                List<Company> result
                        = service.getCompany();

                Platform.runLater(() -> {

                    companies.clear();

                    companies.addAll(result);

                    statusLabel.setText(
                            result.size()
                            + " utilizadores carregados"
                    );

                });

            } catch (Exception e) {

                e.printStackTrace();

                Platform.runLater(() -> {

                    statusLabel.setText(
                            "Erro: "
                            + e.getMessage()
                    );

                });

            }

        });

        thread.setDaemon(true);
        thread.start();
    }
}
