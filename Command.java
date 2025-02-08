interface Command {
    void execute(String[] cmdParts) throws ExInsufficientArguments, ExMissingEquipmentRecord, ExOverlapBorrow, 
        ExOverlapRequest, ExMemberNotFound, ExEquipmentRecordNotFound, ExNoAvailableSet, ExIncorrectNumberOfDays,
        ExEquipmentCodeAlreadyInUse, ExMemberIdAlreadyInUse, ExInvalidDate, ExMemberAlreadyBorrowing;
}
