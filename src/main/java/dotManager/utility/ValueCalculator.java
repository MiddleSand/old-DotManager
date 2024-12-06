package dotManager.utility;

import java.util.Hashtable;
import java.util.Map;

import org.bukkit.Material;

public class ValueCalculator {
	public static Map<String, Object> values;
	public static Map<String, Object> calcCache;
	public static float calculateNewValue(float baseValue, int unitsDemand, int unitsSupply) {
		//FORMULA:  V = b * (D/S)
		float calculated = 0;
		if(unitsDemand == 0 && unitsSupply == 0) {
			
		} else if (unitsSupply == 0) {
			
		} else if (unitsDemand == 0) {
			calculated = baseValue * (1/unitsSupply); //TODO: Add averages for supply and demand
		} else {
			calculated = baseValue * (unitsDemand/unitsSupply);
		}
		return calculated;
	}
	public static String getReferenceForMaterial(Material stack) {
		return stack.name();
	}
	public static float getValue(String item) {
		return 0.0f;
	}
}
