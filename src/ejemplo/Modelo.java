package ejemplo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;
import gurobi.GRBVar;

public class Modelo {

	public static void main(String[] args) {
		try {
			Grafo migrafo = Utilidades.cargarGrafo((args != null && args.length != 0) ? args[0] : "./data/RMC.dat");
			GRBEnv env = new GRBEnv(null);
			GRBModel model = new GRBModel(env);

			for (int i = 0; i < migrafo.getArcos().size(); i++) {
				model.addVar(0, 1, migrafo.getArcos().get(i).getTiempo(), GRB.BINARY, "x(" + migrafo.getArcos().get(i).getCola() + "," + migrafo.getArcos().get(i).getCabeza() + ")");
			}
			model.update();

			for (int i = 0; i < migrafo.getNodos().size(); i++) {
				GRBLinExpr balance = new GRBLinExpr();
				for (int j = 0; j < migrafo.getArcos().size(); j++) {
					if (migrafo.getArcos().get(j).getCola() == migrafo.getNodos().get(i)) {
						balance.addTerm(1, model.getVarByName("x(" + migrafo.getArcos().get(j).getCola() + "," + migrafo.getArcos().get(j).getCabeza() + ")"));
					}
					if (migrafo.getArcos().get(j).getCabeza() == migrafo.getNodos().get(i)) {
						balance.addTerm(-1, model.getVarByName("x(" + migrafo.getArcos().get(j).getCola() + "," + migrafo.getArcos().get(j).getCabeza() + ")"));
					}
				}
				if (i != 0 && i != 14) {
					model.addConstr(balance, GRB.EQUAL, 0, "Balance_" + i);
				} else if (i == 0) {
					model.addConstr(balance, GRB.EQUAL, 1, "Balance_" + i);
				} else {
					model.addConstr(balance, GRB.EQUAL, -1, "Balance_" + i);
				}
			}
			
			GRBLinExpr costo= new GRBLinExpr();
			for(int i=0;i<migrafo.getNodos().size();i++){
				for(int j=0;j<migrafo.getArcos().size();j++){
					if(migrafo.getArcos().get(j).getCabeza()==migrafo.getNodos().get(i)){
					costo.addTerm(migrafo.getArcos().get(j).getCosto(),model.getVarByName("x("+migrafo.getArcos().get(j).getCola()+","+migrafo.getArcos().get(j).getCabeza()+")"));
					}
				}
			}
			model.addConstr(costo,GRB.LESS_EQUAL, 50, "Costo");
			
			GRBLinExpr distancia= new GRBLinExpr();
			for(int i=0;i<migrafo.getNodos().size();i++){
				for(int j=0;j<migrafo.getArcos().size();j++){
					if(migrafo.getArcos().get(j).getCabeza()==migrafo.getNodos().get(i)){
						distancia.addTerm(migrafo.getArcos().get(j).getDistancia(),model.getVarByName("x("+migrafo.getArcos().get(j).getCola()+","+migrafo.getArcos().get(j).getCabeza()+")"));
					}
				}
			}
			model.addConstr(distancia,GRB.LESS_EQUAL, 350, "Distancia");
			
			model.set(GRB.IntAttr.ModelSense, 1);
			model.update();
			model.write("Punto2Tarea4.lp");
			model.optimize();
			
			Map<String, String> mapa = new HashMap<>();
			GRBVar[] variables = model.getVars();
			for (GRBVar variable : variables) {
				String nombre = variable.get(GRB.StringAttr.VarName);
                Integer valor = ((Double)variable.get(GRB.DoubleAttr.X)).intValue();
                if(valor.equals(1)) {
                	String[] split = nombre.replace("x", "").replace("(", "").replace(")", "").trim().split(",");
    				mapa.put(split[0].trim(), split[1].trim());
                }
			}
			
			String clave = "0";
			List<String> respuesta = new ArrayList<>();
			while(respuesta.size() < variables.length) {
				String valor = mapa.get(clave);
				respuesta.add("x(" + clave + "," + valor + ")");
				clave = valor;
			}
			
			String imprimir = "";
			for (String r : respuesta) {
				if(respuesta.indexOf(r) != respuesta.size()-1) {
					imprimir = imprimir + r + " -> ";
				} else {
					imprimir = imprimir + r;
				}
			}
			System.out.println("\n\n-----------RUTA------------");
			System.out.println(imprimir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
