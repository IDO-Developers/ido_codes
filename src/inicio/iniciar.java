package inicio;

import com.birosoft.liquid.LiquidLookAndFeel;

import ventanas.ventana_login;

public class iniciar {

	public static void main(String[] args) {
		try{
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
            //javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
            LiquidLookAndFeel.setLiquidDecorations(true, "mac");
            
            ventana_login principal = new ventana_login();
    		principal.setVisible(true);
    		principal.setLocationRelativeTo(null);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
	}

}
