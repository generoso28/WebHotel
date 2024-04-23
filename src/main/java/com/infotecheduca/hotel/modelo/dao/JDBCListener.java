/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetoweb.servico;

/*
Implementando essa lógica, você assegura que todos os drivers JDBC registrados pela sua aplicação sejam devidamente desregistrados quando a aplicação 
for parada ou reiniciada, evitando assim vazamentos de memória relacionados ao classloader. Isso é especialmente importante em ambientes de produção 
onde as aplicações são frequentemente reiniciadas ou redeployed.



Registrar o Listener no web.xml:
Você precisa adicionar seu listener no arquivo de configuração web.xml da sua aplicação web:


   <listener>
    <listener-class>com.mycompany.projetoweb.modelo.servico.JDBCListener</listener-class>
</listener>
*/
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/*
Quando coloco a anotação @WebListener não é necessário colocar no web.xml

  <listener>
        <listener-class>com.mycompany.projetoweb.servico.JDBCListener</listener-class>
    </listener>


*/

@WebListener
public class JDBCListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Código de inicialização, se necessário.
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Deregistrar drivers JDBC
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("Desregistrando driver JDBC: " + driver);
            } catch (SQLException e) {
                System.out.println("Erro ao desregistrar driver JDBC: " + driver + ", " + e);
            }
        }
    }
}
