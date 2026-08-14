/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mz.co.jmtecnologias.multigest;

/**
 *
 * @author JM-Tecnologias
 */
public class Test {

    public static void main(String[] args) {
        Service service = new Service();
//        try {
//            List<Company> companies = service.getCompany();
//
//            System.out.println("Quantidade de empresas: " + companies.size());
//
//            for (Company company : companies) {
//                System.out.println("___________________________");
//                System.out.println(company);
//            }
//        } catch (Exception e) {
//            System.err.println("Erro ao consumir API");
//            e.printStackTrace();
//        }
        try {
            Company company = new Company("Empresa Teste", "Empresa Teste LDA", 987654321, "Descricao de teste para a empresa");
            System.out.println("Result: "+service.createCompany(company));
            
        } catch (Exception e) {
            System.err.println("Erro ao consumir API");
            e.printStackTrace();
        }
    }
}
