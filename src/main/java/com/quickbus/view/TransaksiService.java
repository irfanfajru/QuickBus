package com.quickbus.view;

import java.util.Map;

public interface TransaksiService {
    public Map save(Transaksi transaksi);
    public Map updateStatus(Transaksi transaksi);
}
