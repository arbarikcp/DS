package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FunctionLib {

  HashMap<List<String>, Function> funcArgMap = new HashMap<>();
  HashMap<List<String>, Function> varFuncArgMap = new HashMap<>();
  Set<Function> functions = new HashSet<>();

  public void register(Set<Function> functionSet) {
    functions.addAll(functionSet);
    for (Function func : functionSet) {
      if(func.isVariadic){
        varFuncArgMap.put(func.argumentTypes, func);
      }else {
        funcArgMap.put(func.argumentTypes, func);
      }
    }
  }

  public List<Function> findMatches(List<String> argumentTypes) {
    List<Function> matchedFunctions = new ArrayList<>();
    if(funcArgMap.containsKey(argumentTypes)){
      matchedFunctions.add(funcArgMap.get(argumentTypes));
    }
    String lastArg = argumentTypes.get(argumentTypes.size() -1);
    int index = argumentTypes.size() -2;
    while (index >= 0){
      String arg = argumentTypes.get(index);
      if(arg.equals(lastArg)){
        List<String> argToCheck = argumentTypes.subList(0, index + 1);
        if(varFuncArgMap.containsKey(argToCheck)){
          matchedFunctions.add(varFuncArgMap.get(argToCheck));
        }
        lastArg = arg;
      }else{
        break;
      }
      index--;
    }
    return matchedFunctions;
  }

  public static void main(String[] args) {
    String s = "String";
    String i = "integer";
    FunctionLib functionLib = new FunctionLib();
    Function f1 = new Function("funcA",Arrays.asList(s,i,i), false);
    Function f2 = new Function("funcb",Arrays.asList(s,i), true);
    Function f3 = new Function("funcc",Arrays.asList(i), true);
    Function f4 = new Function("funcd",Arrays.asList(i,i), true);
    Function f5 = new Function("funce",Arrays.asList(i,i,i), false);
    Function f6 = new Function("funcf",Arrays.asList(s), false);
    Function f7 = new Function("funcg",Arrays.asList(i), false);
    Set<Function> functionSet = new HashSet<>(Arrays.asList(f1,f2,f3,f4,f5,f5,f6,f7));
    functionLib.register(functionSet);
    System.out.println(functionLib.findMatches(Arrays.asList(i,i,i,i)));
  }
}

class Function {
  String name;
  List<String> argumentTypes;
  boolean isVariadic;

  Function(String name, List<String> argumentTypes, boolean isVariadic) {
    this.name = name;
    this.argumentTypes = argumentTypes;
    this.isVariadic = isVariadic;
  }

  @Override
  public String toString(){
    return this.name;
  }
}
