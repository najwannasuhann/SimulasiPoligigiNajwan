package com.example.simulasipoligiginajwan;

public class Konfigurasi {
        //Pasien
        public static final String PASIEN_ADD = "http://192.168.117.201/poligigi/inputpasien.php";
        public static final String URL_GET_ALLPASIEN = "http://192.168.117.201/poligigi/tampilpasien.php";
        public static final String PASIEN_DETAILS = "http://192.168.117.201/poligigi/detailpasien.php?id=";
        public static final String PASIEN_UPDATE = "http://192.168.117.201/poligigi/editpasien.php";
        public static final String PASIEN_HAPUS = "http://192.168.117.201/poligigi/hapuspasien.php?id=";

        //Dokter
        public static final String DOKTER_ADD = "http://192.168.117.201/poligigi/inputdokter.php";
        public static final String URL_GET_ALLDOKTER = "http://192.168.117.201/poligigi/tampildokter.php";
        public static final String DOKTER_DETAILS = "http://192.168.117.201/poligigi/detaildokter.php?id=";
        public static final String DOKTER_UPDATE = "http://192.168.117.201/poligigi/editdokter.php";
        public static final String DOKTER_HAPUS = "http://192.168.117.201/poligigi/hapusdokter.php?id=";

        //Jadwal
        public static final String JADWAL_ADD = "http://192.168.117.201/poligigi/inputdokter.php";
        public static final String URL_GET_ALLJADWAL = "http://192.168.117.201/poligigi/tampiljadwal.php";
        public static final String JADWAL_DETAILS = "http://192.168.117.201/poligigi/detailjadwal.php?id=";
        public static final String JADWAL_UPDATE = "http://192.168.173.109/poligigi/editjadwal.php";
        public static final String JADWAL_HAPUS = "http://192.168.173.109/poligigi/hapusjadwal.php?id=";


        //untuk ke phpnya
        public static final String KEY_PASIEN_IDPASIEN = "id_pasien";
        public static final String KEY_PASIEN_NAMAPASIEN = "nama_pasien";
        public static final String KEY_PASIEN_USIAPASIEN = "usia_pasien";
        public static final String KEY_PASIEN_JKPASIEN = "jk_pasien";
        public static final String KEY_PASIEN_ALAMAT = "alamat";

        //dokter php
        public static final String KEY_DOKTER_IDDOKTER = "id_dokter";
        public static final String KEY_DOKTER_NOSIP = "no_sip";
        public static final String KEY_DOKTER_NAMADOKTER = "nama_dokter";
        public static final String KEY_DOKTER_JKDOKTER = "jk_dokter";

        //Jadwal Php
        public static final String KEY_JADWAL_IDJADWAL = "id_jadwal";
        public static final String KEY_JADWAL_NAMADOKTER = "nama_dokter";
        public static final String KEY_JADWAL_HARI = "jadwal_hari";
        public static final String KEY_JADWAL_JAM = "jadwal_jam";

        //JSON array
        public static final String TAG_JSON_ARRAY="result";
        public static final String TAG_STATUS = "status";

        //untuk di androidnya
        public static final String TAG_IDPASIEN = "id_pasien";
        public static final String TAG_NAMAPASIEN = "nama_pasien";
        public static final String TAG_USIAPASIEN = "usia_pasien";
        public static final String TAG_JKPASIEN = "jk_pasien";
        public static final String TAG_ALAMAT = "alamat";

        //Dokter
        public static final String TAG_IDDOKTER = "id_dokter";
        public static final String TAG_NOSIP = "no_sip";
        public static final String TAG_NAMADOKTER = "nama_dokter";
        public static final String TAG_JKDOKTER = "jk_dokter";

        //Dokter
        public static final String TAG_IDJADWAL = "id_jadwal";
        public static final String TAG_NAMADOK = "nama_dokter";
        public static final String TAG_JADHARI = "jadwal_hari";
        public static final String TAG_JADJAM = "jadwal_jam";

        // ID untuk php dan android
        public static final String EMP_ID = "id";
    }


