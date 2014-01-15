Set oWMP = CreateObject("WMPlayer.OCX.7")
Set colCDROMs = oWMP.cdromCollection
For i = 0 To colCDROMs.count-1 
colCDROMs.item(i).eject
Next
oWMP.close