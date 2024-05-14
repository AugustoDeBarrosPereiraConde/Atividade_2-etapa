import view.ClientePanel;
import view.ProdutoPanel;
import view.VendaPanel;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Sistema de Gestão de Vendas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        
        tabbedPane.addTab("Produtos", new ProdutoPanel());
        tabbedPane.addTab("Clientes", new ClientePanel());
        tabbedPane.addTab("Vendas", new VendaPanel());
        
        add(tabbedPane);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
