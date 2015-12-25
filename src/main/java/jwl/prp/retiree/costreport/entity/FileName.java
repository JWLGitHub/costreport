package jwl.prp.retiree.costreport.entity;


import jwl.prp.retiree.costreport.enums.OrgTypRef;


public class FileName
{
    // Position 1, Length 19
    private String fullName;

    // Position 1. Length 4 - Format "good" or "bad`"
    private String prefix;

    // Position 5, Length 1 - Format "P" or "V"
    private String submitterType;

    // Position 6 - 15, Length 10
    private String submitterID;

    // Position 1. Length 16 - Format "."
    private String extensionSeparator;

    // Positions 17 - 19, Length 3
    private String extension;

    private boolean valid = false;

    private static final int      VALID_FULL_NAME_LENGTH     = 19;

    private static final String[] VALID_PREFIXES             = {"good", "badd"};

    private static final String[] VALID_SUBMITTER_TYPES      = {OrgTypRef.PLAN_SPONSOR.getOrgTypCd(),
                                                                OrgTypRef.VENDOR.getOrgTypCd()};

    private static final String[] VALID_EXTENSION_SEPARATORS = {"."};

    private static final String[] VALID_EXTENSIONS           = {"txt"};


    public FileName()
    {
        validate();
    }


    public FileName(String fullName)
    {
        this.fullName = fullName;
        validate();
    }


    private void validate()
    {
        if (null == fullName                ||
            fullName.trim().length() == 0   ||
            fullName.length() != VALID_FULL_NAME_LENGTH)
            return;

        prefix = fullName.substring(0, 4);
        if (!validValue(prefix, VALID_PREFIXES))
            return;

        submitterType = fullName.substring(4, 5);
        if (!validValue(submitterType, VALID_SUBMITTER_TYPES))
            return;

        submitterID = fullName.substring(5, 15);

        extensionSeparator = fullName.substring(15, 16);
        if (!validValue(extensionSeparator, VALID_EXTENSION_SEPARATORS))
            return;

        extension = fullName.substring(16);
        if (!validValue(extension, VALID_EXTENSIONS))
            return;

        valid = true;
    }


    private boolean validValue(String   value,
                               String[] validValues)
    {
        boolean isValidValue = false;

        for (String validValue : validValues)
        {
            if (value.equalsIgnoreCase(validValue))
            {
                isValidValue = true;
                break;
            }
        }

        return isValidValue;
    }


    // --- Getter(s) ---
    public String getFullName() { return fullName; }

    public String getPrefix() { return prefix; }

    public String getSubmitterType() { return submitterType; }

    public String getSubmitterID() { return submitterID; }

    public String getExtensionSeparator() { return extensionSeparator; }

    public String getExtension() { return extension; }

    public boolean isValid() {  return valid; }


    // --- Setter(s) ---
    public void setFullName(String fullName) { this.fullName = fullName; }

    public void setPrefix(String prefix) { this.prefix = prefix; }

    public void setSubmitterType(String submitterType) { this.submitterType = submitterType; }

    public void setSubmitterID(String submitterID) { this.submitterID = submitterID; }

    public void setExtensionSeparator(String extensionSeparator) { this.extensionSeparator = extensionSeparator; }

    public void setExtension(String extension) { this.extension = extension; }

    public void setValid(boolean valid) { this.valid = valid; }


    @Override
    public String toString()
    {
        return  "FullName: " + fullName + ", " +
                "Prefix: " + prefix + ", " +
                "SubmitterType: " + submitterType + ", " +
                "SubmitterID: " + submitterID + ", " +
                "ExtensionSeparator: " + extensionSeparator + ", " +
                "Extension: " + extension + ", " +
                "Valid: " + valid;
    }
}
