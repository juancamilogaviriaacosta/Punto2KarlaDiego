package ejemplo;

import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;

public class Modelo {

	public static void main(String[] args) {
		try {
			Grafo migrafo = Utilidades.cargarGrafo((args != null && args.length != 0) ? args[0] : "./data/RMC.dat");
			GRBEnv env = new GRBEnv(null);
			GRBModel model = new GRBModel(env);

			for (int i = 0; i < migrafo.getArcos().size(); i++) {
				model.addVar(0, 1, migrafo.getArcos().get(i).getCosto(), GRB.CONTINUOUS, "x(" + migrafo.getArcos().get(i).getCola() + "," + migrafo.getArcos().get(i).getCabeza() + ")");
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
				if (i != 0 && i != 6) {
					model.addConstr(balance, GRB.EQUAL, 0, "Balance_" + i);
				} else if (i == 0) {
					model.addConstr(balance, GRB.EQUAL, 1, "Balance_" + i);
				} else {
					model.addConstr(balance, GRB.EQUAL, -1, "Balance_" + i);
				}
			}
			model.set(GRB.IntAttr.ModelSense, 1);
			model.update();
			model.write("EjemploRMC.lp");
			model.optimize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
