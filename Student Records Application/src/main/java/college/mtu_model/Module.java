package college.mtu_model;

/**
 * This class represents a module in a college curriculum.
 * It contains information about the module name, code, and semester.
 */
public class Module {

    private String moduleName;
    private String moduleCode;
    private int semester;

    /**
     * Constructs a new Module object with the given module name, code, and semester.
     *
     * @param moduleName The name of the module.
     * @param moduleCode The code of the module.
     * @param semester   The semester in which the module is taught.
     */
    public Module(String moduleName, String moduleCode, int semester) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.semester = semester;
    }

    public Module(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Module Name: " + moduleName +
                " | " +
                "Module Code: " + moduleCode +
                " | " +
                "Semester: " + semester +
                "\n";
    }

    public String toString2() {
        return "Module Name: " + moduleName + " | ";
    }
}
