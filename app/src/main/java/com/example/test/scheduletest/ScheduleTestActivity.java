package com.example.test.scheduletest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.baselibrary.schedule.Schedule;
import com.example.test.R;

public class ScheduleTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_test);

        initView();
    }

    private void initView() {
        findViewById(R.id.bt_ui_to_ui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zgf", "====currentThread====" + Thread.currentThread().getName());
                Schedule.getInstance().runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("zgf", "====currentThread==run==" + Thread.currentThread().getName());
                    }
                });
            }
        });

        findViewById(R.id.bt_sync_to_ui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("zgf", "====currentThread====" + Thread.currentThread().getName());
                        Schedule.getInstance().runOnMainThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("zgf", "====currentThread==run==" + Thread.currentThread().getName());
                            }
                        });
                    }
                });
                thread.start();
            }
        });

        findViewById(R.id.bt_ui_to_sync).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zgf", "====currentThread====" + Thread.currentThread().getName());
                Schedule.getInstance().runOnBackground(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("zgf", "====currentThread==run==" + Thread.currentThread().getName());
                    }
                });
            }
        });

        findViewById(R.id.bt_sync_to_sync).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("zgf", "====currentThread====" + Thread.currentThread().getName());
                        Schedule.getInstance().runOnBackground(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("zgf", "====currentThread==run==" + Thread.currentThread().getName());
                            }
                        });
                    }
                });
                thread.start();
            }
        });

        findViewById(R.id.bt_thread_mode_ui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zgf", "====currentThread====" + Thread.currentThread().getName());
                Schedule.ThreadMode mode = Schedule.ThreadMode.ASYNC;
                Schedule.getInstance().runWithThreadMode(mode, new Runnable() {
                    @Override
                    public void run() {
                        Log.e("zgf", "====currentThread==run==" + Thread.currentThread().getName());
                    }
                });
            }
        });

        findViewById(R.id.bt_thread_mode_async).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("zgf", "====currentThread====" + Thread.currentThread().getName());
                        Schedule.ThreadMode mode = Schedule.ThreadMode.ASYNC;
                        Schedule.getInstance().runWithThreadMode(mode, new Runnable() {
                            @Override
                            public void run() {
                                Log.e("zgf", "====currentThread==run==" + Thread.currentThread().getName());
                            }
                        });
                    }
                });
                thread.start();
            }
        });

        findViewById(R.id.bt_ui_delay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zgf", "====currentThread====" + Thread.currentThread().getName());
                Schedule.getInstance().runOnMainThread(2000, new Runnable() {
                    @Override
                    public void run() {
                        Log.e("zgf", "====currentThread==run==" + Thread.currentThread().getName());
                    }
                });
            }
        });

        findViewById(R.id.bt_multi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zgf", "====currentThread====" + Thread.currentThread().getName());

//                multiRun();
//                multiRunAsync();
                multiRunAsync2();
            }
        });
    }

    private void multiRun() {
        Schedule.getInstance().runOnMainThread(2000, new Runnable() {
            @Override
            public void run() {
                Log.e("zgf", "====currentThread==2000=1=" + Thread.currentThread().getName());
            }
        });

        Schedule.getInstance().runOnMainThread(2000, new Runnable() {
            @Override
            public void run() {
                Log.e("zgf", "====currentThread==2000=2=" + Thread.currentThread().getName());
            }
        });

        Schedule.getInstance().runOnMainThread(new Runnable() {
            @Override
            public void run() {
                Log.e("zgf", "====currentThread==run=1=" + Thread.currentThread().getName());
            }
        });

        Schedule.getInstance().runOnMainThread(new Runnable() {
            @Override
            public void run() {
                Log.e("zgf", "====currentThread==run=2=" + Thread.currentThread().getName());
            }
        });
    }

    private void multiRunAsync() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("zgf", "====currentThread====" + Thread.currentThread().getName());
                multiRun();
            }
        });
        thread.start();
    }

    private void multiRunAsync2() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("zgf", "====currentThread====" + Thread.currentThread().getName());
                Schedule.getInstance().runWithThreadMode(Schedule.ThreadMode.ASYNC, new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.e("zgf", "====currentThread==run==1=200=" + Thread.currentThread().getName());
                    }
                });

                Schedule.getInstance().runWithThreadMode(Schedule.ThreadMode.ASYNC, new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.e("zgf", "====currentThread==run==2=400=" + Thread.currentThread().getName());
                    }
                });

                Schedule.getInstance().runWithThreadMode(Schedule.ThreadMode.ASYNC, new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.e("zgf", "====currentThread==run==3=400=" + Thread.currentThread().getName());
                    }
                });

                Schedule.getInstance().runWithThreadMode(Schedule.ThreadMode.ASYNC, new Runnable() {
                    @Override
                    public void run() {
                        Log.e("zgf", "====currentThread==run=4=" + Thread.currentThread().getName());
                    }
                });
            }
        });
        thread.start();
    }
}